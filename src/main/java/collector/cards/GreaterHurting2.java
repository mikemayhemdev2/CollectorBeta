
package collector.cards;

import collector.effects.PurpleSearingBlowEffect;
import collector.powers.AddCopyNextTurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class GreaterHurting2 extends AbstractCollectorCard {
    public final static String ID = makeID(GreaterHurting2.class.getSimpleName());

    public GreaterHurting2() {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 21;
        isEthereal = true;
        cardsToPreview = new GreatestHurting2();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new PurpleSearingBlowEffect(m.hb.cX, m.hb.cY, 4)));
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard toAdd = new GreatestHurting2();
        if (upgraded){
            toAdd.upgrade();
        }
        applyToSelfTop(new AddCopyNextTurnPower(toAdd));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void upp() {
        upgradeDamage(7);
        uDesc();
        cardsToPreview.upgrade();
    }
}

