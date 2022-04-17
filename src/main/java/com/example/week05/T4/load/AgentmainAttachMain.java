//package com.example.week05.T4.load;
//
//import com.sun.tools.attach.VirtualMachine;
//import com.sun.tools.attach.VirtualMachineDescriptor;
//
//import java.util.List;
//
//public class AgentmainAttachMain {
//    public static void main(String[] args) throws Exception {
//        String path = "";
//        List<VirtualMachineDescriptor> list = VirtualMachine.list();
//        for (VirtualMachineDescriptor descriptor : list) {
//            if (descriptor.displayName().endsWith("AgentTargetSample")) {
//                VirtualMachine virtualMachine = VirtualMachine.attach(descriptor.id());
//                virtualMachine.loadAgent("D:\\action\\agent\\target\\agent-1.0.jar", "arg1");
//                virtualMachine.detach();
//            }
//        }
//    }
//}