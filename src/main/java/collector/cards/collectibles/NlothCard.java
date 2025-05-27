package collector.cards.collectibles;

import collector.cards.OnPyreCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class NlothCard extends AbstractCollectibleCard implements OnPyreCard {
    public final static String ID = makeID(NlothCard.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public NlothCard() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        isPyre();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard toGenerate = AbstractDungeon.srcRareCardPool.getRandomCard(AbstractDungeon.cardRandomRng).makeCopy();
        if (upgraded) {
            toGenerate.updateCost(-999);
        }
        else {
            toGenerate.updateCost(-1);
        }

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (pyredKindling){
                    toGenerate.upgrade();
                }
                makeInHand(toGenerate);
            }
        });
    }

    boolean pyredKindling = false;
    @Override
    public void onPyred(AbstractCard card) {
        pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
    }

    public void upp() {
        uDesc();
    }
}