package collector.cards.collectibles;

import collector.actions.DrawAllShapesFromCollectionAction;
import collector.cards.Ember;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import expansioncontent.expansionContentMod;

import static collector.CollectorMod.SHAPESWARM;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class ExploderCard extends AbstractCollectibleCard {
    public final static String ID = makeID(ExploderCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 3, 1

    public ExploderCard() {
        super(ID, -2, CardType.ATTACK, CardRarity.COMMON, CardTarget.NONE);
        baseDamage = 12;
        this.tags.add(SHAPESWARM);
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void triggerOnExhaust() {
        atb(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.damage, false), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new DrawAllShapesFromCollectionAction());
    }

    public void upp() {
        upgradeDamage(3);
    }
}