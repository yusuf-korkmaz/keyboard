package com.klavye.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yusufkorkmaz on 11.22.2017.
 */
public class Neuron {
    private Node node ;
    private int time ;
    private int standartSapma;
    private int weight;
    private List<Integer> dataTimes;

    public Neuron(Node c_node,int c_time){
        this.dataTimes =new ArrayList<>();
        this.node=c_node;
        this.dataTimes.add(c_time);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStandartSapma() {
        return standartSapma;
    }

    public void setStandartSapma(int standartSapma) {
        this.standartSapma = standartSapma;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<Integer> getDataTimes() {
        return dataTimes;
    }

    public void setDataTimes(List<Integer> dataTimes) {
        this.dataTimes = dataTimes;
    }
}
