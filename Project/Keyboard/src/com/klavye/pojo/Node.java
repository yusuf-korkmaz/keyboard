package com.klavye.pojo;

/**
 * Created by yusufkorkmaz on 11.22.2017.
 */
public class Node {
    private String first;
    private String last;

    public Node(String c_first,String c_last){
        this.first=c_first;
        this.last=c_last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String  first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String  last) {
        this.last = last;
    }
}
