package expansioncontent.cards;
import collector.cards.OnPyreCard;
import collector.powers.AddCopyNextTurnPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.cards.AbstractCollectorCard.pyreOrange;


public class SuperSomberShield extends AbstractExpansionCard implements OnPyreCard {
    public final static String ID = makeID(SuperSomberShield.class.getSimpleName());
    // intellij stuff skill, self, common, , , 7, 3, ,

    public SuperSomberShield() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 9;
        this.setBackgroundTexture("expansioncontentResources/images/512/bg_boss_collector.png", "expansioncontentResources/images/1024/bg_boss_collector.png");

        tags.add(expansionContentMod.STUDY_COLLECTOR);
        tags.add(expansionContentMod.STUDY);

        isPyre();
        expansionContentMod.loadJokeCardImage(this, "SuperSomberShield.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void onPyred(AbstractCard card) {
        if (card.tags.contains(expansionContentMod.KINDLING)){
            card.upgrade();
        }
        applyToSelfTop(new AddCopyNextTurnPower(card));
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