package collector.cards;

import automaton.actions.EasyXCostAction;
import collector.effects.ColoredVerticalAttackEffect;
import collector.powers.DoomPower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class ScorchingRay extends AbstractCollectorCard {
    public final static String ID = makeID(ScorchingRay.class.getSimpleName());
    // intellij stuff attack, enemy, common, 4, 1, , , 4,
    //Hello this card is overrated, thanks for coming to my ted talk.

    public ScorchingRay() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        //baseDamage = 9;
        magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        att(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                //ScorchingRayAction couldn't cut it.
                //AbstractMonster q = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                atb(new VFXAction(new ColoredVerticalAttackEffect(m.hb.x + MathUtils.random(m.hb.width / 3, ((m.hb.width / 3) * 2)), m.hb.cY, true, new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1))));
                //this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.FIRE));
                applyToEnemy(m, new DoomPower(m, magicNumber));
            }
            return true;
        }));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}