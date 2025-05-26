package collector.cards.collectibles;

import collector.cards.OnPyreCard;
import collector.powers.StrengthOverTurnsPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class CultistCard extends AbstractCollectibleCard implements OnPyreCard {
    public final static String ID = makeID(CultistCard.class.getSimpleName());
    // intellij stuff power, self, common, , , , , , 

    public CultistCard() {
        super(ID, 1, CardType.POWER, CardRarity.COMMON, CardTarget.SELF);
        isPyre();
        this.baseMagicNumber = magicNumber = 3;
        this.baseSecondMagic = secondMagic = 1;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (pyredKindling){
                    applyToSelf(new StrengthOverTurnsPower(magicNumber+secondMagic, 1));
                }else{
                    applyToSelf(new StrengthOverTurnsPower(magicNumber, 1));
                }
            }
        });

    }

    boolean pyredKindling = false;
    @Override
    public void onPyred(AbstractCard card) {
        pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}