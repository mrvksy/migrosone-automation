package com.migrosone.mobiletestautomation.test

import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.printToLog
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.migrosone.mobiletestautomation.MainActivity
import com.migrosone.mobiletestautomation.constants.SignInConstants
import com.migrosone.mobiletestautomation.constants.SignUpConstants
import com.migrosone.mobiletestautomation.pages.InitialScreenPage
import com.migrosone.mobiletestautomation.pages.SignInPage
import com.migrosone.mobiletestautomation.pages.SignUpPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SingInScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    private val signInPage by lazy { SignInPage(composeTestRule) }
    private val signUpPage by lazy { SignUpPage(composeTestRule) }
    private val initialScreenPage by lazy { InitialScreenPage(composeTestRule) }


    @Test
    fun userShouldSignInWhileAfterSingUpE2E() {
        // 1. Ana ekran: logo ve butonlar geldiği görülür
        signUpPage.assertHomePageFieldsVisible()

        // 2. Sign Up butonuna tıklanır
        signUpPage.clickOnSingUpButton()

        // 3. Sign Up başlığı kontrolü
        signUpPage.assertSignUpTitleExist()

        // 4. Alanların geldiği görülür
        signUpPage.assertSignUpFieldsDisplayed()

        // 5. Formu doldur
        signUpPage.fillFormSignUpPage()

        // 6. Sign Up butonuna tıklanır
        signUpPage.clickOnSignUpButtonAllNodesWithText()

        // 7. Başarı mesajı geldiği görülür
        signUpPage.waitSuccessMembershipMessage()
        signUpPage.assertSuccessMembershipMessage()

        // 8. Sign In Now butonun geldiği görülür
        // signUpPage.assertSignInNowButtonDisplayed()

        // 9. Sign In Now butonuna tıklanır
        signUpPage.clickOnSignInNowButton()

        //10. Sign In Ekranın açıldığı görülür
        signUpPage.assertSignInScreenOpen()

        //11. Sign In Ekranındaki Email alanı görülür
        signInPage.checkEmailFieldDisplayed()

        //12. Sign In Ekranındaki Password alanı görülür
        signInPage.checkPasswordFieldDisplayed()

        //13.Sing In Ekranın açıldığı görülür
        signInPage.assertSignInScreenOpen()

        composeTestRule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)
            .printToLog("TEXTFIELDS")

        //12. Sign Up olan Kullanıcının Email girilir
        signInPage.setEmailAddressToForm()

        //13. Sign Up olan Kullanıcının Password girilir
        signInPage.setValidPasswordToTextField()

        composeTestRule
            .onAllNodesWithText(SignUpConstants.SIGN_IN_TEXT, useUnmergedTree = true)
            .printToLog("SIGN_IN_BUTTON_CHECK")

        //14. Sign In Butonuna tıklanır
        signInPage.clickOnSignInButton()

        //15. Profil resmi gelene kadar beklenir
        signInPage.waitProfileImage()

        //16. Profil resminin geldiği görülür
        signInPage.assertProfileImageDisplayed()

        //15. Welcome User Name Başlığı görülür
        signInPage.assertWelcomeUserTitleDisplayed()

        //16.Email Adresi görülür
        signInPage.assertEmailAddressFromWelcomePageDisplayed()

        //17. Sign out butonu ve Additional Info texti görülür
        signInPage.assertAdditionalInfoTextDisplayed()

        //18. Sign Out Butonu görülür
        signInPage.assertSignOutButtonDisplayed()

        //19. Sign Out Butonuna tıklanır
        signInPage.clickOnSignOutButton()

        //20. Sign In Ekranın açıldığı görülür
        signUpPage.assertSignInScreenOpen()

    }

    @Test
    fun userShouldNotSignInWithIncorretCredentials() {
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

        //7. Sign In Ekranındaki Email alanı görülür
        signInPage.checkEmailFieldDisplayed()

        //8. Sign In Ekranındaki Password alanı görülür
        signInPage.checkPasswordFieldDisplayed()

        //9.Sing In Ekranın açıldığı görülür
        signInPage.assertSignInScreenOpen()

        composeTestRule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)
            .printToLog("TEXTFIELDS")

        //10. Kayıtlı olmayan Kullanıcının Email girilir
        signInPage.setIncorretCreadentialsEmailAddressToForm()

        //11. Kayıtlı Olmayan olan Kullanıcının Password girilir
        signInPage.setIncorrectCredentialsPasswordToTextField()

        //Close Keyboard
        composeTestRule.onNodeWithText(SignInConstants.PASSWORD_LABEL, useUnmergedTree = true)
            .performImeAction()

        composeTestRule
            .onAllNodesWithText(SignUpConstants.SIGN_IN_TEXT, useUnmergedTree = true)
            .printToLog("SIGN_IN_BUTTON_CHECK")

        //12. Sign In Butonuna tıklanır
        signInPage.clickOnSignInButton()

        //13. Incorrect User Credentials Mesajı görülür
        signInPage.checkIncorrectUserCredentialsMessage()

    }
}