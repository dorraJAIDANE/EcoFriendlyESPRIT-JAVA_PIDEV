/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACES;

import Models.Semestre;
import Models.Topic;
import java.util.List;

/**
 *
 * @author Dorra
 */
public interface TopicService {
     List<Topic> getAllTopics();
    Topic getTopicById(int id);
    void modifierTopic(int idTopic, String nouveauNom);
    void addTopic(Topic t);

     boolean supprimerTopic(int idTopic);
}
