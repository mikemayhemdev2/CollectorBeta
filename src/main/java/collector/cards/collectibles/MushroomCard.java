package collector.cards.collectibles;

import collector.powers.collectioncards.MushroomDamagePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sneckomod.SneckoMod;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class MushroomCard extends AbstractCollectibleCard {
    public final static String ID = makeID(MushroomCard.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 5, 1, , , 3, 1

    public MushroomCard() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 4;
        //baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 2;
//        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        if (AbstractDungeon.cardRandomRng.randomBoolean()) {
            applyToEnemy(m, new WeakPower(m, magicNumber, false));
        } else {
            applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        }
        applyToSelf(new MushroomDamagePower(secondMagic));
    }


    public void applyPowers() {
        if (AbstractDungeon.player.hasPower(MushroomDamagePower.POWER_ID)){
            this.baseDamage = 1 + AbstractDungeon.player.getPower(MushroomDamagePower.POWER_ID).amount;
            this.isDamageModified = true;
        } else {
            this.baseDamage = 1;
        }
        super.applyPowers();
    }


    public void upp() {
        //upgradeDamage(1);
        upgradeSecondMagic(2);
       // upgradeMagicNumber(1);
    }
}