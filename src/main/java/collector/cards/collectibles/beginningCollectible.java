package collector.cards.collectibles;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sneckomod.SneckoMod;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;

public class beginningCollectible  extends AbstractCollectibleCard {
    public final static String ID = makeID(beginningCollectible.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 5, 1, , , 3, 1

    public beginningCollectible() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 1;
//        this.exhaust = true;
//        this.isEthereal = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
        /*
        if (AbstractDungeon.floorNum >= 7){//Wheeeeeee
            AbstractCard c = this;
            AbstractDungeon.effectsQueue.add(new PurgeCardEffect(c));
            CollectorCollection.collection.removeCard(c);
        }
         */
    }

    public void upp() {
        upgradeDamage(40);
        upgradeMagicNumber(403);
    }
}
