package song.utils;

import com.google.common.eventbus.EventBus;

/**
 * Created by Song on 2015/10/9.
 */
public class EventBusHelper {
    private static final EventBus eventBus = new EventBus();


    public void register(Object object){
        eventBus.register(object);
    }


    public void post(Object event){
        eventBus.post(event);
    }
    public static EventBus getEventBus() {
        return eventBus;
    }
}
