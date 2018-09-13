package com.klavye.util;

import com.klavye.pojo.Neuron;
import com.klavye.pojo.NeuronNetwork;
import com.klavye.pojo.Node;

import java.io.*;

/**
 * Created by yusufkorkmaz on 11.22.2017.
 */
public class ArtificialNeuronNetworkUtils {
    private NeuronNetwork neuronNetwork;
    public ArtificialNeuronNetworkUtils(){
        this.neuronNetwork = new NeuronNetwork();
    }

    public NeuronNetwork getNeuronNetwork() {
        return neuronNetwork;
    }

    public void setNeuronNetwork(NeuronNetwork neuronNetwork) {
        this.neuronNetwork = neuronNetwork;
    }

    public void dataRead(String m_sifre) {
        //TODO txt oku .. ayırıdğında node oluştur node varmı diye bak varsa süreyi ekle yoksa node ekle
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(m_sifre+".txt"));
            String currentLine="";

            while((currentLine = bufferedReader.readLine()) != null){

                currentLine = currentLine.trim();
                String neurons[] = currentLine.split("/");

                for (int i = 0; i < neurons.length; i++) {
                    String currentNode[] = neurons[i].split("-");
                    String firstCharacter = currentNode[0];
                    String secondCharacter = currentNode[1];
                    int time = Integer.valueOf(currentNode[2].toString());

                    Node readedNode = new Node(firstCharacter,secondCharacter);
                    boolean isAdded = false;
                    for (Neuron current: neuronNetwork.getNeurons()) {
                        if(NeuronUtils.isEquals(current.getNode(),readedNode)){
                            current.getDataTimes().add(time);
                            isAdded = true;
                        }
                    }
                    if(!isAdded){
                        Neuron addThisNeuron = new Neuron(readedNode,time);
                        neuronNetwork.getNeurons().add(addThisNeuron);
                    }


                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            File file = new File(m_sifre+".txt");
            try {
                file.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void dataWrite(String m_sifre,String m_currentLine){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(m_sifre+".txt",true));
            bufferedWriter.write(m_currentLine);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            File file = new File(m_sifre+".txt");
            try {
                file.createNewFile();
                dataWrite(m_sifre,m_currentLine);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    public void calculateTime(){
        int j=0;
        //TODO olması gereken süreyi hesapla ata
        for (Neuron current:neuronNetwork.getNeurons()) {
            int toplam = 0;
            for (int i = 0; i < current.getDataTimes().size(); i++) {
                toplam += current.getDataTimes().get(i);
            }
            int time = toplam / current.getDataTimes().size();
            current.setTime(time);
            System.out.println(j+")"+time);
            j++;
        }
        System.out.println("standart sapma");
        calculateStandartSapma();
    }

    public void calculateStandartSapma(){
        int j=0;
        //TODO standart sapma yı hesapla sürelere göre
        for (Neuron current :neuronNetwork.getNeurons()) {
            int toplamStandartSapma= 0;
            for (int i = 0; i < current.getDataTimes().size(); i++) {
                toplamStandartSapma+=(Math.pow(current.getDataTimes().get(i)-current.getTime(),2));
            }
            int standartSapma = (int)Math.sqrt((toplamStandartSapma/current.getDataTimes().size()));
            current.setStandartSapma(standartSapma);
            System.out.println(j+")"+standartSapma);
            j++;
        }
        System.out.println("weight");
        calculateWeight();

    }

    public void calculateWeight(){
        //TODO weight hesapla ve ata sürelere göre
        int j=0;
        for (Neuron current:neuronNetwork.getNeurons()) {
            int weight=0;
            for (int i = 0; i < current.getDataTimes().size(); i++) {
                int currentTime = current.getDataTimes().get(i);
                if(current.getTime()+current.getStandartSapma() >= currentTime &&
                        current.getTime()-current.getStandartSapma() <= currentTime){
                    weight += 6;
                }
                else{
                    weight += 1;
                }
            }
            current.setWeight(weight);
            System.out.println(j+")"+weight);
            j++;
        }

    }

    public void calculateTreshold(String m_sifre){
        //TODO geçmesi gereken eşiği belirle
        int thresholdToplamWeight=0;
        int weightToplamTekSatır=0;
        int threshold = 0;
        int j = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(m_sifre+".txt"));
            String currentLine = "";
            while((currentLine = bufferedReader.readLine()) != null){
                weightToplamTekSatır = getThresholdForThisString(currentLine);
                System.out.println(j + ")" + weightToplamTekSatır);
                j++;
                thresholdToplamWeight += weightToplamTekSatır;
            }
            threshold = thresholdToplamWeight / j;
            threshold -= (threshold%25); //burasi
            neuronNetwork.setThreshold(threshold);
            System.out.println("-");
            System.out.println(threshold + "-" + thresholdToplamWeight);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public int getThresholdForThisString(String m_satir){
        m_satir+= m_satir.trim();
        int weightToplamTekSatır = 0;
        String neurons[] = m_satir.split("/");
        for (int i = 0; i < neurons.length; i++) {
            String node[]= neurons[i].split("-");
            String firstKarakter = node[0].toString();
            String secondKarakter = node[1].toString();
            int readedTime = Integer.valueOf(node[2].toString());

            Node readedNode = new Node(firstKarakter,secondKarakter);
            for (Neuron currentNeuron: neuronNetwork.getNeurons()) {
                if(NeuronUtils.isEquals(currentNeuron.getNode(),readedNode)){
                    if(currentNeuron.getTime()+currentNeuron.getStandartSapma() >= readedTime &&
                            currentNeuron.getTime()-currentNeuron.getStandartSapma() <= readedTime){
                        weightToplamTekSatır += currentNeuron.getWeight();
                    }
                }
            }
        }
        return weightToplamTekSatır;
    }

    public String toString(String m_sifre){
        dataRead(m_sifre);

        for (int i = 0; i < neuronNetwork.getNeurons().size(); i++) {
            System.out.print(i + ")" +neuronNetwork.getNeurons().get(i).getNode().getFirst() +"-"+
                    neuronNetwork.getNeurons().get(i).getNode().getLast() +"-");
            for (int a:neuronNetwork.getNeurons().get(i).getDataTimes()) {
                System.out.print(a+"-");
            }
            System.out.println("");
        }
        return null;
    }


}
