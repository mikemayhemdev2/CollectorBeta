package collector.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import utilityClasses.DFL;

public class LeechAction extends AbstractGameAction {
    private final int energyGainAmt;
    private final DamageInfo info;

    public LeechAction(AbstractCreature target, DamageInfo info, int energyAmt) {
        this.info = info;
        setValues(target, info);
        this.energyGainAmt = energyAmt;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {
            this.target.damage(this.info);
            if (this.target.isDying || this.target.currentHealth <= 0) {
                DFL.atb(new GainReservesAction(energyGainAmt));
            }
            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        tickDuration();
    }
}
