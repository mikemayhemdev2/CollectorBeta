package collector.cards.collectibles;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import static utilityClasses.Wiz.*;

import static collector.CollectorMod.makeID;

public class AugmenterCard extends AbstractCollectibleCard {
    public final static String ID = makeID(AugmenterCard.class.getSimpleName());
    // intellij stuff power, self, uncommkon, , , , , 3, 2

    public AugmenterCard() {
        super(ID, 1, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new OfferingEffect(), 0.1F));
        atb(new DamageAction(p, new DamageInfo(p, magicNumber, DamageInfo.DamageType.THORNS)));
        applyToSelf(new StrengthPower(p, secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(-2);
        upgradeSecondMagic(2);
        uDesc();
    }
}