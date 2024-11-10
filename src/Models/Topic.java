/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Dorra
 */
public class Topic {
    private int idTopic;
    private String topicName;

    public Topic(int idTopic, String topicName) {
        this.idTopic = idTopic;
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "Topic{" + "idTopic=" + idTopic + ", topicName=" + topicName + '}';
    }

    public Topic() {
    }

    public Topic(int idTopic) {
        this.idTopic = idTopic;
    }
    

   
    

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

}
