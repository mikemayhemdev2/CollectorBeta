package collector.cards.collectibles;

import collector.CollectorCollection;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class BronzeOrbCard extends AbstractCollectibleCard {
    public final static String ID = makeID(BronzeOrbCard.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 5, 2, 5, 2, , 

    public BronzeOrbCard() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseBlock = 4;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public boolean searchPile(CardGroup cardsToSearch) {

        for (AbstractCard c : cardsToSearch.group) {
            if (c instanceof Hyperbeam && c.cost > 0) {
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
        upgradeDamage(2);
        upgradeBlock(2);
    }
}