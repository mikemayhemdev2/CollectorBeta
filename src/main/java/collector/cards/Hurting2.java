package collector.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import collector.effects.PurpleSearingBlowEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.makeInHand;

public class Hurting2 extends AbstractCollectorCard {
    public final static String ID = makeID(Hurting2.class.getSimpleName());

    public Hurting2() {//Look at the bottom of the next one
        super(ID, 1, CardType.ATTACK, AbstractCard.CardRarity.UNCOMMON, CardTarget.ENEMY);
        isEthereal = true;
        baseDamage = 10;
        MultiCardPreview.add(this, new GreaterHurting2(), new GreatestHurting2());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new PurpleSearingBlowEffect(m.hb.cX, m.hb.cY, 2)));
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard toAdd = new GreaterHurting();
        if (upgraded) {
            toAdd.upgrade();
        }
        makeInHand(toAdd);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void upp() {
        uDesc();
        upgradeDamage(3);
        MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
    }

}
