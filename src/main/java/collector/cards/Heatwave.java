package collector.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.makeInHand;

public class Heatwave extends AbstractCollectorCard {
    public final static String ID = makeID(Heatwave.class.getSimpleName());
    // intellij stuff attack, all_enemy, uncommon, 12, 4, , , , 

    public Heatwave() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 14;
        cardsToPreview = new Ember();
        isMultiDamage = true;
        this.baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int q = 0; q < 6; q++) {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new ShockWaveEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, new Color(MathUtils.random(1.0f), MathUtils.random(0, 0.2f), MathUtils.random(0, 0.2f), 1.0f), ShockWaveEffect.ShockWaveType.NORMAL)));
        }
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        makeInHand(new Ember(), magicNumber);
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}