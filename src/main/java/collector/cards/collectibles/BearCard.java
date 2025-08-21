package collector.cards.collectibles;

import collector.CollectorMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import guardian.cards.BodySlam;
import sneckomod.SneckoMod;

import java.util.Arrays;

import static collector.CollectorMod.banditBoost;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;
import static utilityClasses.Wiz.*;

public class BearCard extends AbstractCollectibleCard {
    public final static String ID = makeID(BearCard.class.getSimpleName());
    // intellij stuff attack, enemy, uncommon, 10, 3, 10, 3, , 

    public BearCard() {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 4;
        baseBlock = 3;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = p.currentBlock + (this.block*this.magicNumber);
        calculateCardDamage(m);
        for (int mn = 0; mn < magicNumber; mn++) {
            addToTop(new GainBlockAction(p, this.block));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));
        rawDescription = DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void applyPowers() {
        this.baseDamage = AbstractDungeon.player.currentBlock + (this.block*this.magicNumber);
        super.applyPowers();
        rawDescription = DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        this.baseDamage = AbstractDungeon.player.currentBlock + (this.block*this.magicNumber);
        super.calculateCardDamage(mo);
        rawDescription = DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void upp() {
        upgradeBlock(1);
    }

}