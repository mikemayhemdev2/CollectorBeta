package collector.cards;

import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.makeInHand;

public class FleetingEmbers extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(FleetingEmbers.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 2, 1

    public FleetingEmbers() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        baseBlock = 6;
        cardsToPreview = new Ember();
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void onPyred(AbstractCard card) {
        boolean pyredKindling;
        if (card.tags.contains(expansionContentMod.KINDLING)){
            pyredKindling = true;
        }else{
            pyredKindling = false;
        }
        Ember e = new Ember();
        if (pyredKindling){
            e.upgrade();
        }
        makeInHand(e.makeStatEquivalentCopy(), magicNumber);
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
        upgradeBlock(3);
    }
}