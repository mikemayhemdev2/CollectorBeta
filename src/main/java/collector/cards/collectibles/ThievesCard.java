package collector.cards.collectibles;

import collector.powers.collectioncards.ThievesCardPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import sneckomod.SneckoMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class ThievesCard extends AbstractCollectibleCard {
    public final static String ID = makeID(ThievesCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 1, 1

    public ThievesCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new SmokeBombEffect(DFL.pl().hb.cX, DFL.pl().hb.cY)));
        applyToSelf(new ThievesCardPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}