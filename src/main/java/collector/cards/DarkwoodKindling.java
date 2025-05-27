package collector.cards;

import collector.actions.GainReservesAction;
import collector.powers.NextTurnReservePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;

public class DarkwoodKindling extends AbstractCollectorCard {
    public final static String ID = makeID(DarkwoodKindling.class.getSimpleName());
    // intellij stuff skill, none, common, , , , , 10, 4

    public DarkwoodKindling() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void triggerOnExhaust() {
        CardCrawlGame.sound.play("CARD_EXHAUST", 0.2F);
        CardCrawlGame.sound.play("CARD_EXHAUST", 0.2F);
        addToBot(new ApplyPowerAction(DFL.pl(), DFL.pl(), new NextTurnReservePower(magicNumber), magicNumber));
        if (upgraded) addToBot(new ApplyPowerAction(DFL.pl(), DFL.pl(), new EnergizedPower(DFL.pl(), 1)));
    }

    public void upp() {
        //upgradeMagicNumber(1);
        uDesc();
    }
}