package collector.cards;

import collector.CollectorCollection;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightBulbEffect;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.makeInHand;

public class BrainDrain extends AbstractCollectorCard {
    public final static String ID = makeID(BrainDrain.class.getSimpleName());
    // intellij stuff skill, enemy, uncommon, , , , , , 

    public BrainDrain() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        exhaust = true;
        baseMagicNumber = magicNumber = 6;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new LoseHPAction(m, p, magicNumber));
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new LightBulbEffect(m.hb)));
        } else {
            this.addToBot(new VFXAction(new LightBulbEffect(m.hb), 0.2F));
        }
        AbstractCard q = CollectorCollection.getCollectedCard(m);
        if (upgraded) {
            q.upgrade();
        }
        q.cost = 0;
        q.costForTurn = 0;
        q.isCostModified = (q.makeCopy().cost != 0);
        makeInHand(q);
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}