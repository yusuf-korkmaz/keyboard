package com.klavye.util;

import com.klavye.pojo.Node;

/**
 * Created by yusufkorkmaz on 11.22.2017.
 */
public class NeuronUtils {
    public static boolean isEquals(Node first,Node second){
        if(first.getFirst().equals(second.getFirst())){
            if(first.getLast().equals(second.getLast()))
                return true;
        }
        return false;
    }
}
