package collector.cards.collectibles;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import collector.CollectorCollection;
import collector.cards.GreaterHurting2;
import collector.cards.GreatestHurting2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.cards.HyperBeam;
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
        MultiCardPreview.add(this, new Hyperbeam(), new HyperBeam(), new automaton.cards.HyperBeam());
    }

    public boolean searchPile(CardGroup cardsToSearch) {

        for (AbstractCard c : cardsToSearch.group) {
            if (c instanceof Hyperbeam && c.cost > 0) {//Defect one
                c.updateCost(-1);
                return true;
            }
        }

        for (AbstractCard c : cardsToSearch.group) {
            if (c instanceof HyperBeam && c.cost > 0) {//Auto one from boss pool
                c.updateCost(-1);
                return true;
            }
        }

        for (AbstractCard c : cardsToSearch.group) {
            if (c instanceof automaton.cards.HyperBeam && c.cost > 0) {//The other auto one, it drops from act 2 boss.
                c.updateCost(-1);
                return true;
            }
        }
        return false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.NONE);

            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (searchPile(AbstractDungeon.player.hand)) return;
                    if (searchPile(AbstractDungeon.player.drawPile)) return;
                    if (searchPile(AbstractDungeon.player.discardPile)) return;
                    if (searchPile(CollectorCollection.combatCollection)) return;
                    searchPile(AbstractDungeon.player.exhaustPile);
                }
            });


        }


    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
    }
}