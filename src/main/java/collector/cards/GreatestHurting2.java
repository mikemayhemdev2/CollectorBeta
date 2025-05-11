package collector.cards;

import collector.effects.PurpleSearingBlowEffect;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.makeInHand;

public class GreatestHurting2 extends AbstractCollectorCard {
    public final static String ID = makeID(GreatestHurting2.class.getSimpleName());

    public GreatestHurting2() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 32;
        isEthereal = true;
//        cardsToPreview = new Hurting2(); - No circular references!
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new PurpleSearingBlowEffect(m.hb.cX, m.hb.cY, 6)));
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void triggerOnExhaust() {
        AbstractCard toAdd = new Hurting();
        if (upgraded){
            toAdd.upgrade();
        }
        makeInHand(toAdd);
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void upp() {
        upgradeDamage(10);
        uDesc();
//        cardsToPreview.upgrade();
    }
}
