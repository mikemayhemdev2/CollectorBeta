package collector.cards;

import collector.CollectorCollection;
////import collector.actions.DrawCardFromCollectionAction;
import collector.actions.DrawCollectedCardAction;
import collector.relics.HolidayCoal;
import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.att;

public class Soulforge extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(Soulforge.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , 8, 3, ,

    private boolean pyreBonus = false;

    public Soulforge() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 9;
        baseMagicNumber = magicNumber = 1;
        //exhaust = true;
        isPyre();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();

        if (pyreBonus) {
            for (int i = 0; i < this.magicNumber; i++) {
                this.addToBot(new DrawCollectedCardAction(1));
            }
        }

        pyreBonus = false;
        /*

         if (!CollectorCollection.combatCollection.isEmpty() || AbstractDungeon.player.hasRelic(HolidayCoal.ID)) {

            for (int i = 0; i < magicNumber; i++)
                atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        isDone = true;
                        for (AbstractCard q : DrawCardAction.drawnCards) {
                            AbstractCard dupe = q.makeStatEquivalentCopy();
                            if (dupe.canUpgrade()) {
                                dupe.upgrade();
                            }
                            att(new MakeTempCardInHandAction(dupe, 1, true));
                        }
                    }
                });
              }
         */
    }


    @Override
    public void onPyred(AbstractCard card) {
        if (card.tags.contains(expansionContentMod.KINDLING)){
            pyreBonus = true;
        }else {
            pyreBonus = false;
        }
    }

    public void upp() {
        upgradeBlock(3);
        //upgradeMagicNumber(1);
        //uDesc();
    }
}

