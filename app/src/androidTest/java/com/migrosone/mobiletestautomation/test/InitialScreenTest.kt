package com.migrosone.mobiletestautomation.test

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.migrosone.mobiletestautomation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.migrosone.mobiletestautomation.pages.InitialScreenPage

@RunWith(AndroidJUnit4::class)
class InitialScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private val initialScreenPage by lazy { InitialScreenPage(composeTestRule) }


    @Test
    fun signInAndSignUpButtonsAreVisibleAndOpenSignInPage() {
        //1. Sign In Butonu görülür
        initialScreenPage.assertCheckSignInButtonDisplayed()

        //2. Sign In Butonu Tıklanabilir mi?
        initialScreenPage.assertSignInButtonHasClickAction()

        //3. Sing Up Butonu görülür
        initialScreenPage.assertCheckSignUpButtonDisplayed()

        //4. Sing Up Butonu Tıklanabilir mi?
        initialScreenPage.assertSignUpButtonHasClickAction()

        //5. Migros Logosu görülür
        initialScreenPage.assertCheckMigrosLogoDisplayed()

        //6. Sign In butonuna tıklanır
        initialScreenPage.clickOnSignInButton()

        initialScreenPage.waitEmailFieldForSignInForm()
        //7. Sign In Ekranının Açıldığı görülür
        initialScreenPage.assertCheckSignInFormFields()
        Thread.sleep(3000)

    }


    @Test
    fun signInAndSignUpButtonsAreVisibleAndOpenSignUpPage() {
        //1. Sign In Butonu görülür
        initialScreenPage.assertCheckSignInButtonDisplayed()

        //2. Sign In Butonu Tıklanabilir mi?
        initialScreenPage.assertSignInButtonHasClickAction()

        //3. Sing Up Butonu görülür
        initialScreenPage.assertCheckSignUpButtonDisplayed()

        //4. Sing Up Butonu Tıklanabilir mi?
        initialScreenPage.assertSignUpButtonHasClickAction()

        //5. Migros Logosu görülür
        initialScreenPage.assertCheckMigrosLogoDisplayed()

        //6. Sign Up butonuna tıklanır
        initialScreenPage.clickOnSingUpButton()

        //7. Sign Up Ekranının Açıldığı görülür
        initialScreenPage.assertSignUpFieldsDisplayed()
        Thread.sleep(3000)

    }


}