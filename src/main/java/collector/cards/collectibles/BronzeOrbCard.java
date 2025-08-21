package collector.cards.collectibles;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import collector.CollectorCollection;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.cards.HyperBeam;
import guardian.cards.HyperBeam_Guardian;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class BronzeOrbCard extends AbstractCollectibleCard {
    public final static String ID = makeID(BronzeOrbCard.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 5, 2, 5, 2, , 

    public BronzeOrbCard() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 6;
        baseBlock = 5;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        MultiCardPreview.add(this, new Hyperbeam(), new HyperBeam(), new automaton.cards.HyperBeam(), new HyperBeam_Guardian());
    }

    public void searchPile(CardGroup cardsToSearch) {//Now a void with new discount behaviour

        for (AbstractCard c : cardsToSearch.group) {
            if (c instanceof Hyperbeam && c.cost > 0) {//Defect one
                c.updateCost(-1);
                c.isCostModified = true;
                c.costForTurn = c.cost;
                c.isCostModifiedForTurn = true;
//                return true;
            }
        }

        for (AbstractCard c : cardsToSearch.group) {
            if (c instanceof HyperBeam && c.cost > 0) {//Auto one from boss pool
                c.updateCost(-1);
                c.isCostModified = true;
                c.costForTurn = c.cost;
                c.isCostModifiedForTurn = true;
//                return true;
            }
        }

        for (AbstractCard c : cardsToSearch.group) {
            if (c instanceof automaton.cards.HyperBeam && c.cost > 0) {//The other auto one, it drops from act 2 boss.
                c.updateCost(-1);
                c.isCostModified = true;
                c.costForTurn = c.cost;
                c.isCostModifiedForTurn = true;
//                return true;
            }
        }

        for (AbstractCard c : cardsToSearch.group) {
            if (c instanceof HyperBeam_Guardian && c.cost > 0) {//Giga beam.
                c.updateCost(-1);
                c.isCostModified = true;
                c.costForTurn = c.cost;
                c.isCostModifiedForTurn = true;
//                return true;
            }
        }
//        return false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.NONE);

            atb(new AbstractGameAction() {
                @Override
                public void update() {//Search each pile.
                    isDone = true;
                    searchPile(AbstractDungeon.player.hand);
                    searchPile(AbstractDungeon.player.drawPile);
                    searchPile(AbstractDungeon.player.discardPile);
                    searchPile(CollectorCollection.combatCollection);
                    searchPile(AbstractDungeon.player.exhaustPile);
                }
            });


        }


    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
    }
}