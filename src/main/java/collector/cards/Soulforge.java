package collector.cards;

import collector.CollectorCollection;
import collector.actions.DrawCardFromCollectionAction;
import collector.powers.NextTurnReservePower;
import collector.relics.HolidayCoal;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.att;

public class Soulforge extends AbstractCollectorCard {
    public final static String ID = makeID(Soulforge.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 8, 3, , 

    public Soulforge() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
//        baseBlock = 6;
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
        exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.secondMagic; i++) {
            DFL.atb(new UpgradeRandomCardAction());
        }
        DFL.atb(new ApplyPowerAction(DFL.pl(), DFL.pl(), new NextTurnReservePower(this.magicNumber), this.magicNumber));
    }

    public void upp() {
//        upgradeMagicNumber(1);
        upgradeSecondMagic(3);
//        uDesc();
    }
}

