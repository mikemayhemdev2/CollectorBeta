package collector.cards.collectibles;

import collector.cards.OnPyreCard;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;
import utilityClasses.DFL;

import java.util.concurrent.atomic.AtomicBoolean;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class ClericCard extends AbstractCollectibleCard implements OnPyreCard {
    public final static String ID = makeID(ClericCard.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , 10, 4

    public ClericCard() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 8;
        isPyre();
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    static boolean exhaustIfTrue2 = false;//Note a mechanism like this only works if one instance is doing stuff at any given time!
    public void use(AbstractPlayer p, AbstractMonster m) {

        exhaustIfTrue2 = TempHPField.tempHp.get(p) <= 0;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!pyredKindling){
                    ClericCard.exhaustIfTrue2 = true;
                }
            }
        });
        atb(new AddTemporaryHPAction(p, p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(4);
    }

    boolean pyredKindling = false;
    @Override
    public void onPyred(AbstractCard card) {
        pyredKindling = card.tags.contains(expansionContentMod.KINDLING);
    }
}