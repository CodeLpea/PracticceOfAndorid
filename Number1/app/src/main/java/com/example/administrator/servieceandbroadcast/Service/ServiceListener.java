package com.example.administrator.servieceandbroadcast.Service;

/**
 * Author by lp,on 2019/1/18/018,14:32.
 */
public interface ServiceListener {
   void onOpen();
   void onClose();
   void onAction(int i);
}
