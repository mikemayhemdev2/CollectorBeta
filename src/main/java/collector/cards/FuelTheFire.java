package collector.cards;

import collector.powers.NextTurnReservePower;
import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import downfall.downfallMod;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class FuelTheFire extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(FuelTheFire.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 2, 1

    public FuelTheFire() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            applyToSelf(new DrawCardNextTurnPower(DFL.pl(), 1));
        }
    }

    @Override
    public void onPyred(AbstractCard card) {
        boolean pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
        applyToSelf(new NextTurnReservePower(magicNumber));
        if (pyredKindling){
            applyToSelf(new NextTurnReservePower(1));
        }
    }

    @Override
    public void triggerOnGlowCheck() {//Common glowy effect for Fueled cards.
        if (AbstractDungeon.player != null && AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom() != null
                && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !DFL.pl().hand.isEmpty()) {
            if (DFL.pl().hand.group.stream().anyMatch(c -> c.tags.contains(expansionContentMod.KINDLING))) {
                this.glowColor = pyreOrange;
                return;
            }
        }
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
      //  upgradeMagicNumber(1);
        uDesc();
    }
}