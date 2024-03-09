package com.greatgame.actions;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.greatgame.application.dialogs.TalkDialog;
import com.greatgame.behaviour.CreatureBehaviour;
import com.greatgame.environment.Action;

import static com.greatgame.explorationBehaviourState.ExplorationBehaviourState.PIXELS_PER_METER;

public class TalkAction extends Action {
    Dialog talkDialog;
    CreatureBehaviour client;
    CreatureBehaviour vendor;
    ActionValidator validator;
    Stage uiStage;

    public TalkAction(CreatureBehaviour client, CreatureBehaviour vendor) {
        super("Talk");
        this.client = client;
        this.vendor = vendor;
        talkDialog = new TalkDialog(client.getCreature(), vendor.getCreature());
        validator = new NearToValidator(client, vendor, 3 * PIXELS_PER_METER);
    }

    public void setUiStage(Stage uiStage) {
        this.uiStage = uiStage;
    }

    @Override
    public boolean finished() {
        return !talkDialog.isVisible();
    }

    @Override
    public boolean validate() {
        return validator.validate(client.getEnvironment());
    }

    @Override
    public void start() {
        talkDialog.show(uiStage);
    }

    @Override
    public void update(float delta) {

    }
}
