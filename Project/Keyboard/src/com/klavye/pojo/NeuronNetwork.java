package com.klavye.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yusufkorkmaz on 11.22.2017.
 */
public class NeuronNetwork {
    private int threshold;
    private List<Neuron> neurons;

    public NeuronNetwork(){
        this.neurons = new ArrayList<>();
    }


    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }

}
