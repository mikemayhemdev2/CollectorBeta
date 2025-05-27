package collector.cards;

import automaton.AutomatonChar;
import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import champ.ChampChar;
import collector.CollectorChar;
import collector.actions.GainReservesAction;
import collector.util.CollectorOrangeTextInterface;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import downfall.downfallMod;
import expansioncontent.expansionContentMod;
import guardian.characters.GuardianCharacter;
import slimebound.characters.SlimeboundCharacter;
import theHexaghost.TheHexaghost;
import utilityClasses.DFL;

import java.util.ArrayList;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.att;

public class ReceiveTribute extends AbstractCollectorCard implements OnPyreCard {
    public final static String ID = makeID(ReceiveTribute.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , 2, 2

    public ReceiveTribute() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        isPyre();
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    private static ArrayList<AbstractCard> possibilities;

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (possibilities == null) {
            possibilities = new ArrayList<>();
            for (AbstractCard q : CardLibrary.getAllCards()) {
                if (q.rarity != AbstractCard.CardRarity.SPECIAL && q.hasTag(expansionContentMod.STUDY) && !q.hasTag(AbstractCard.CardTags.HEALING)) {

                    /*
                    if (AbstractDungeon.player instanceof SlimeboundCharacter) {
                        if (q.hasTag(expansionContentMod.STUDY_SLIMEBOSS)){continue;}
                    } else if (AbstractDungeon.player instanceof TheHexaghost) {
                        if(q.hasTag(expansionContentMod.STUDY_HEXAGHOST)){continue;}
                    } else if (AbstractDungeon.player instanceof GuardianCharacter) {
                        if(q.hasTag(expansionContentMod.STUDY_GUARDIAN)){continue;}
                    } else if (AbstractDungeon.player instanceof ChampChar) {
                        if(q.hasTag(expansionContentMod.STUDY_CHAMP)){continue;}
                    } else if (AbstractDungeon.player instanceof AutomatonChar) {
                        if(q.hasTag(expansionContentMod.STUDY_AUTOMATON)){continue;}
                    } else if (AbstractDungeon.player instanceof CollectorChar) {
                        if(q.hasTag(expansionContentMod.STUDY_COLLECTOR)){continue;}
                    }Too mungus and not listed on the card! what if I wanted a whirling flame and thought I was just getting unlucky!!!
                     */

                    AbstractCard r = q.makeCopy();
                    possibilities.add(r);
                }
            }
        }
        ArrayList<AbstractCard> remaining = new ArrayList<>(possibilities);
        ArrayList<AbstractCard> choices = new ArrayList<>();
        for (int i = 0; i < magicNumber; i++) {
            AbstractCard toAdd = remaining.remove(AbstractDungeon.cardRandomRng.random(remaining.size() - 1)).makeCopy();
            choices.add(toAdd);
        }

        atb(new AbstractGameAction() {
        @Override
        public void update() {
            isDone = true;
            if (pyredKindling) {
                choices.forEach(AbstractCard::upgrade);
            }
        }});

        addToBot(new SelectCardsCenteredAction(choices, cardStrings.EXTENDED_DESCRIPTION[0], (cards) -> {
            att(new MakeTempCardInHandAction(cards.get(0), true));
        }));
    }

    public void upp() {
        upgradeMagicNumber(2);
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
            this.keywords.add(GameDictionary.RETAIN.NAMES[0].toLowerCase());
        }
    }
}