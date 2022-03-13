## GC 策略总结

**查看回收策略：**`java -XX:+PrintCommandLineFlags -vesion`

### 一、Parallel GC

parallel gc 又称并行gc，它是基于分代堆模型的回收器，且parallel gc有两种组合方式。

1. `-XX:+UseParallelGC`，年轻代用 parallel scavenge，老年代使用 serial old
2. `-XX:+UseParallelOldGC`，年轻代用 parallel scavenge，老年代使用 parallel old



#### 1.Parallel Scavenge

采用**标记-复制**算法，其目的是尽可能**缩短 STW **的时间，而且试图达到一个可控的吞吐量。其提供了两个参数来控制吞吐量

- -XX:MaxGCPausMillis
- -XX:GCTimeRatio

也可以使用 -XX:+UseAdaptiveSizePolicy 自适应调节策略，如 eden、survivor大小，晋升年龄等

### 二、CMS

`-XX:+UseConcMarkSweepGC`开启 CMS 垃圾收集器，cms 仅作用于老年代，使用标记清楚算法，所以会产生碎片。

与其搭配使用的年轻代收集器使用 parNew。



**CMS 处理流程：**

- Initial Mark。stw
- concurrent-mark
- concurrent-preclean。负责处理上一阶段标记后发生改变的对象，可以减少remark工作量从而减少 stw 耗时
- concurrent-abortable-preclean。同样是减少remark工作量，减少stw。增加这一阶段是为了让我们可以控制这个阶段的结束时机，比如扫描多长时间（默认5秒）或者Eden区使用占比达到期望比例（默认50%）就结束本阶段。
- remark （CMS Final Remark），stw
- concurrent-sweep
- concurrent-reset



cms 使用三色标记算法。

**CMS对多标的处理：**

多标产生浮动垃圾。当前线程栈中有一个引用 obj，初始标记时会作为 gc root，随着业务线程的执行，obj已经没有引用关系可以被回收掉，但是obj已经被标为黑色对象不会再进行扫描，成为浮动垃圾，留到下一次gc处理。



**CMS对漏标的处理：**

漏标黑色对象会让 gc 回收掉正在使用的数据，会带来严重bug。

- CM采用的是写屏障 + 增量更新 避免这种问题。
- G1： 采用的是写屏障 + 原始快照（SATB）
- ZGC：采用的是读屏障



可以使用 `-XX:+CMSScavengeBeforeRemark`尝试在 remark 之前进行一次 ygc，这样在remark时就不需要扫描太多的Eden区对象，也就减少了 stw 耗时。



### 三、G1

G1提供了两种GC模式，Young GC和Mixed GC。

- Young GC：选定所有年轻代里的Region。通过控制年轻代的region个数，即年轻代内存大小，来控制young GC的时间开销

- Mixed GC：选定所有年轻代里的Region，外加根据global concurrent marking统计得出收集收益高的若干老年代Region。在用户指定的开销目标范围内尽可能选择收益高的老年代Region

![image-20220313224612182](/Users/ltx/Documents/img-repo//image-20220313224612182.png)



