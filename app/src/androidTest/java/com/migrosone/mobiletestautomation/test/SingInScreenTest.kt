package com.migrosone.mobiletestautomation.test

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.migrosone.mobiletestautomation.MainActivity
import com.migrosone.mobiletestautomation.constants.SignInScreenConstants
import com.migrosone.mobiletestautomation.pages.InitialScreenPage
import com.migrosone.mobiletestautomation.pages.SignInScreenPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var signInScreenPage: SignInScreenPage
    private val initialScreenPage by lazy { InitialScreenPage(composeTestRule) }

    @Before
    fun setUp() {
        signInScreenPage = SignInScreenPage(composeTestRule)
    }

    // Giriş ekranındaki alanların görüntülendiği ve geçerli bilgilerle giriş yapılabildiği doğrulanmalıdır.
    @Test
    fun signInScreenFieldsAreVisibleAndValidLogin() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        initialScreenPage.assertCheckMigrosLogoDisplayed()
        initialScreenPage.assertCheckSignInButtonDisplayed()
        Thread.sleep(1000)

        // 2. "Sign In" butonuna tıklanır ve Form alanı beklenir.
        initialScreenPage.clickOnSignInButton()
        initialScreenPage.waitEmailFieldForSignInForm() // <- Artık başarısız olmamalı
        Thread.sleep(1000)

        // 3. Geçerli email ve Geçerli şifre bilgisi girilir.
        signInScreenPage.setEmailAddressToForm()
        signInScreenPage.setValidPasswordToTextField()
        Thread.sleep(1000)

        // Klavye kapatılır.
        Espresso.closeSoftKeyboard()

        // 4. "Sign In" butonuna tıklandığı doğrulanır.
        signInScreenPage.clickOnSignInButton()
        Thread.sleep(1000)

        // 5. Kullanıcının giriş yaptığı doğrulanır.
        signInScreenPage.assertProfileImageDisplayed()
        Thread.sleep(2000)

    }



    // Geçersiz bilgilerle giriş yapılamadığı ve hata mesajının görüntülendiği doğrulanmalıdır.
    @Test
    fun signInFailsWithIncorrectCredentialsAndErrorMessageIsDisplayed() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        initialScreenPage.assertCheckMigrosLogoDisplayed()
        initialScreenPage.assertCheckSignInButtonDisplayed()
        Thread.sleep(1000)

        // 2. "Sign In" butonuna tıklanır ve Form alanı beklenir.
        initialScreenPage.clickOnSignInButton()
        initialScreenPage.waitEmailFieldForSignInForm()
        Thread.sleep(1000)

        // 3. Geçersiz email ve geçersiz şifre bilgisi girilir.
        signInScreenPage.setIncorrectCredentialsEmailAddressToForm()
        signInScreenPage.setIncorrectCredentialsPasswordToTextField()
        Thread.sleep(1000)

        // Klavye kapatılır.
        Espresso.closeSoftKeyboard()

        // 4. "Sign In" butonuna tıklandığı doğrulanır.
        signInScreenPage.clickOnSignInButton()
        Thread.sleep(1000)

        // 5. Kullanıcının hata mesajını aldığı doğrulanır.
        signInScreenPage.checkIncorrectUserCredentialsMessage()
    }

    // Email ve Şifre bilgisi boş bırakıldığında zorunlu alan olduğu hata mesajını göstermelidir.
    @Test
    fun signInWithEmptyEmailAndPassword_showsErrorMessages() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        initialScreenPage.assertCheckMigrosLogoDisplayed()
        initialScreenPage.assertCheckSignInButtonDisplayed()
        Thread.sleep(1000)

        // 2. "Sign In" butonuna tıklanır ve Form alanı beklenir.
        initialScreenPage.clickOnSignInButton()
        initialScreenPage.waitEmailFieldForSignInForm()
        Thread.sleep(1000)

        // 3. E-mail ve şifre alanları boş olacak şekil bilgi girişi yapılır.
        signInScreenPage.fillCredentials(email = "", password = "")
        Thread.sleep(500)

        // Klavye kapatılır.
        Espresso.closeSoftKeyboard()

        // 4. "Sign In" butonuna tıklanır.
        signInScreenPage.clickOnSignInButton()
        Thread.sleep(1000)

        // 5. Kullanıcının hata mesajı aldığı doğrulanır.
        composeTestRule.onNodeWithText(SignInScreenConstants.EMPTY_EMAIL_MESSAGE, useUnmergedTree = true)
            .assertExists()
        composeTestRule.onNodeWithText(SignInScreenConstants.EMPTY_PASSWORD_MESSAGE, useUnmergedTree = true)
            .assertExists()
    }


    // Email boş bırakılıp ve Şifre bilgisi girildiğinde emailin zorunlu alan olduğu hata mesajını göstermelidir.
    @Test
    fun signInshowsRequiredFieldErrorswhenEmailEmpty() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        initialScreenPage.assertCheckMigrosLogoDisplayed()
        initialScreenPage.assertCheckSignInButtonDisplayed()
        Thread.sleep(1000)

        // 2. "Sign In" butonuna tıklanır ve Form alanı beklenir.
        initialScreenPage.clickOnSignInButton()
        initialScreenPage.waitEmailFieldForSignInForm()
        Thread.sleep(1000)

        // 3. E-mail ve şifre alanları boş olacak şekil bilgi girişi yapılır.
        signInScreenPage.fillCredentials(email = "", password = "ksy")
        Thread.sleep(500)

        // Klavye kapatılır.
        Espresso.closeSoftKeyboard()

        // 4. "Sign In" butonuna tıklanır.
        signInScreenPage.clickOnSignInButton()
        Thread.sleep(1000)

        // 5. Kullanıcının hata mesajı aldığı doğrulanır.
        composeTestRule.onNodeWithText(SignInScreenConstants.EMPTY_EMAIL_MESSAGE, useUnmergedTree = true)
            .assertExists()
        Thread.sleep(1000)
    }


    // Email bilgisi girilip ve Şifre boş bırakıldığında şifre alanının zorunlu  alan olduğu hata mesajını göstermelidir.
    @Test
    fun signInshowsRequiredFieldErrorswhenPasswordEmpty() {
        //1. Ana ekrana butonların ve logonun geldiği doğrulanır.
        initialScreenPage.assertCheckMigrosLogoDisplayed()
        initialScreenPage.assertCheckSignInButtonDisplayed()
        Thread.sleep(1000)

        // 2. "Sign In" butonuna tıklanır ve Form alanı beklenir.
        initialScreenPage.clickOnSignInButton()
        initialScreenPage.waitEmailFieldForSignInForm()
        Thread.sleep(1000)

        // 3. E-mail ve şifre alanları boş olacak şekil bilgi girişi yapılır.
        signInScreenPage.fillCredentials(email = "mrv", password = "")
        Thread.sleep(500)

        // Klavye kapatılır.
        Espresso.closeSoftKeyboard()

        // 4. "Sign In" butonuna tıklanır.
        signInScreenPage.clickOnSignInButton()
        Thread.sleep(1000)

        // 5. Kullanıcının hata mesajı aldığı doğrulanır.
        composeTestRule.onNodeWithText(SignInScreenConstants.EMPTY_PASSWORD_MESSAGE, useUnmergedTree = true)
            .assertExists()
        Thread.sleep(1000)
    }


}