package collector.cards.collectibles;

import collector.CollectorCollection;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;
import static utilityClasses.Wiz.att;

public class MysticCard extends AbstractCollectibleCard {
    public final static String ID = makeID(MysticCard.class.getSimpleName());
    // intellij stuff skill, self, common, , , , , 6, 2

    public MysticCard() {
        super(ID, -2, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 5;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        tags.add(expansionContentMod.UNPLAYABLE);
        tags.add(expansionContentMod.KINDLING);
//        tags.add(CardTags.HEALING);
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void searchPile(CardGroup cardsToSearch) {
    }

    @Override
    public void triggerOnExhaust() {
        atb(new AddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}