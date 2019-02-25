package XQBHServer.Utils.CallUtils;

import java.util.HashMap;
import java.util.Map;

public class CallResult {
    private Map<String,String> head;
    private Map<String,String> body;
    public CallResult(){
        head=new HashMap();
        body=new HashMap();
    }

    public Map<String,String> getHead() {
        return head;
    }

    public void setHead(Map head) {
        this.head = head;
    }

    public Map<String,String> getBody() {
        return body;
    }

    public void setBody(Map body) {
        this.body = body;
    }
}
