package collector.cards;

import basemod.helpers.CardModifierManager;
import collector.cardmods.ActuallyCollectedCardMod;
import collector.cardmods.CollectedCardMod;
import collector.cards.collectibles.AbstractCollectibleCard;
import collector.cards.collectibles.SentryCard;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import sneckomod.SneckoMod;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;
import static utilityClasses.Wiz.atb;

public class SentryWave extends AbstractCollectibleCard {
    public final static String ID = makeID(SentryWave.class.getSimpleName());

    public SentryWave() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 2;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.exhaust = true;
    }//Sentry Waves

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, magicNumber, false));
    }

    @Override
    public void triggerOnExhaust() {
        SentryCard card = new SentryCard();
        if (this.upgraded){
            card.upgrade();
        }
        CardModifierManager.addModifier(card, new CollectedCardMod());
        CardModifierManager.addModifier(card, new ActuallyCollectedCardMod());
        atb(new MakeTempCardInHandAction(card ,1));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}
