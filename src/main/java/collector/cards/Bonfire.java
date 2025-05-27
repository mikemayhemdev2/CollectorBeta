package collector.cards;

import collector.effects.GreenflameBarrierEffect;
import collector.powers.NextTurnReservePower;
import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import downfall.downfallMod;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToSelf;
import static utilityClasses.Wiz.atb;

public class Bonfire extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(Bonfire.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 20, 5, , 

    public Bonfire() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 12;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(p, new GreenflameBarrierEffect(p.hb.cX, p.hb.cY), 0.1F));
        } else {
            this.addToBot(new VFXAction(p, new GreenflameBarrierEffect(p.hb.cX, p.hb.cY), 0.5F));
        }

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                blck();
                if (pyredKindling){
                    applyToSelf(new NextTurnReservePower(1));
                }
            }
        });
    }


    public void upp() {
        upgradeBlock(4);
    }

    boolean pyredKindling = false;
    @Override
    public void onPyred(AbstractCard card) {
        pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
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

    @Override
    public void initializeDescription() {
        super.initializeDescription();
        if (!this.upgraded) {
            String get = downfallMod.keywords_and_proper_names.get("reserve");
            this.keywords.add(get);
        }
    }
}