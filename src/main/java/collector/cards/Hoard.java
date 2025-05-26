package collector.cards;

import basemod.helpers.CardModifierManager;
import collector.util.CollectorOrangeTextInterface;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import downfall.downfallMod;
import expansioncontent.cardmods.PropertiesMod;
import expansioncontent.expansionContentMod;
import utilityClasses.DFL;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class Hoard extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(Hoard.class.getSimpleName());
    boolean pyredKindling = false;

    public Hoard() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
        baseSecondMagic = secondMagic = 1;
        isPyre();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (pyredKindling) {
                    /*
                    for (AbstractCard q : DrawCardAction.drawnCards) {
                        q.superFlash(Color.GREEN.cpy());
                        PropertiesMod mod = new PropertiesMod();
                        if (!q.selfRetain)
                            mod.addProperty(PropertiesMod.supportedProperties.RETAIN, true);
                        if (!mod.bonusPropertiesForThisTurn.isEmpty()) {
                            CardModifierManager.addModifier(q, mod);
                        }
                    }
                     */
                    atb(new DrawCardAction(secondMagic));
                }
            }
        });

    }

    public void upp() {
        upgradeSecondMagic(1);
    }

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

    /*
    @Override
    public void initializeDescription() {
        super.initializeDescription();
        if (!this.upgraded) {

            //String get = "retain";
            //String get = downfallMod.keywords_and_proper_names.get("Stslib:retain");
            this.keywords.add(GameDictionary.RETAIN.NAMES[0].toLowerCase());
        }
    }
    */
}