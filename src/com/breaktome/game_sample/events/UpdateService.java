package com.breaktome.game_sample.events;

import com.shawnclake.morgencore.core.component.services.ListService;

public class UpdateService extends ListService<IUpdate> {

    public void trigger() throws Exception {
        for (IUpdate update : this.getItems())
        {
            if(!update.requiresUpdates())
            {
                this.getItems().remove(update);
                continue;
            }

            update.update();
        }
    }

    @Override
    public void add(IUpdate item) {
        this.getItems().add(item);
    }
}
