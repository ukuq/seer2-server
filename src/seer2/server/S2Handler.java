package seer2.server;

import seer2.hu.Message;

public interface S2Handler{
    void parse(Message m, S2User s2User);
}
