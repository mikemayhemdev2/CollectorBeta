package collector.cards;

import collector.util.CollectorOrangeTextInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class JadedJabs extends AbstractCollectorCard implements OnPyreCard, CollectorOrangeTextInterface {
    public final static String ID = makeID(JadedJabs.class.getSimpleName());
    // intellij stuff attack, enemy, common, 10, 2, , , , 
    Shiv thisShiv = new Shiv();

    public JadedJabs() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;

        cardsToPreview = thisShiv;
        isPyre();
    }

    private int toAdd = -1;

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        AbstractCard q = new Shiv();
        if (this.upgraded){
            q.upgrade();
        }
//        makeInHand(q, magicNumber);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (toAdd >= 1) {
                    att(new MakeTempCardInHandAction(q, toAdd, true));
                }
            }
        });
    }

    @Override
    public void onPyred(AbstractCard card) {
        if (card.tags.contains(expansionContentMod.KINDLING)){
            toAdd = this.magicNumber;
        }else {
            int result = freeToPlay() ? 0 : card.costForTurn;
            toAdd = result > 0 ? result : -1;
        }
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
        thisShiv.upgrade();
        uDesc();
    }
}