package ru.cft.shift.task6.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgConvert {
     private static final Logger log = LoggerFactory.getLogger(MsgConvert.class);

     public MsgConvert() {
     }

     public String convertToString(MessageType messageType, String msg){
          String str = null;
          Message message = new Message(messageType, msg);
          ObjectMapper mapper = new ObjectMapper();
          try {
               str = mapper.writeValueAsString(message);
          }catch (JsonProcessingException e){
               log.error("ошибка json: " + e);
          }
          return str;
     }

     public Message covertToMessage(String msg){
          Message message = null ;
          ObjectMapper mapper = new ObjectMapper();
          try {
               message = mapper.readValue(msg, Message.class);
          }catch (Exception e){
               log.error("ошибка json: " + e);
          }
          return message;
     }
}

