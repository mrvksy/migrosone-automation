package com.migrosone.mobiletestautomation.pages

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.click
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import com.migrosone.mobiletestautomation.constants.SignUpConstants
import com.migrosone.mobiletestautomation.constants.SignInConstants


class SignInPage(private val rule: ComposeTestRule) {
    fun checkEmailFieldDisplayed() {
        rule.onNodeWithText(SignInConstants.EMAIL_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun checkPasswordFieldDisplayed() {
        rule.onNodeWithText(SignInConstants.PASSWORD_LABEL, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun assertSignInScreenOpen() {
        rule
            .onAllNodesWithText(SignInConstants.SIGN_IN_TEXT, useUnmergedTree = true)[0]
            .assertIsDisplayed()
    }

    fun fillCredentials(email: String, password: String) {
        val fields = rule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)
        fields[0].performClick().performTextInput(email)
        fields[1].performClick().performTextInput(password)
    }

    fun setEmailAddressToForm() {
        // Email
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[0]
            .performTextInput(SignInConstants.VALID_EMAIL)
    }

    fun setValidPasswordToTextField() {
        val textFields = rule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)
        textFields[1].performClick().performTextInput(SignInConstants.VALID_PASSWORD)
    }

    fun clickOnSignInButton() {
        val nodes = rule.onAllNodesWithText(SignUpConstants.SIGN_IN_TEXT, useUnmergedTree = true)
        if (nodes.fetchSemanticsNodes().size > 1) {
            try {
                nodes[1].performClick()
            } catch (e: Exception) {
                nodes[1].performTouchInput { click() }
            }
        }
        /*
         try {
             rule.onAllNodesWithText(SignUpConstants.SIGN_IN_TEXT, useUnmergedTree = true) [1]
                 .performClick()
         } catch (e: AssertionError) {
             rule
                 .onAllNodesWithText(SignInConstants.SIGN_IN_TEXT, useUnmergedTree = true)[1]
                 .performTouchInput { click() }
         }
         rule.onAllNodesWithText(SignUpConstants.SIGN_IN_TEXT, useUnmergedTree = true) [1]
             .performClick()
        */

        /*
        rule
            .onAllNodesWithText(SignInConstants.SIGN_IN_TEXT, useUnmergedTree = true)[1]
            .performTouchInput { click() }

         */
    }

    fun waitProfileImage() {
        rule.waitUntil(timeoutMillis = 10000) {
            rule
                .onAllNodesWithContentDescription(
                    SignInConstants.PROFILE_PICTURE_DESC,
                    useUnmergedTree = true
                )
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
    }

    fun assertProfileImageDisplayed() {
        rule
            .onNodeWithContentDescription(
                SignInConstants.PROFILE_PICTURE_DESC,
                useUnmergedTree = true
            )
            .assertIsDisplayed()
    }

    fun assertWelcomeUserTitleDisplayed() {
        rule.onNodeWithText(
            SignInConstants.WELCOME_TEXT + SignInConstants.VALID_NAME,
            useUnmergedTree = true
        )
            .assertIsDisplayed()
    }

    fun assertEmailAddressFromWelcomePageDisplayed() {
        rule.onNodeWithText(
            SignInConstants.EMAIL_DISPLAY + SignInConstants.VALID_EMAIL, useUnmergedTree = true
        )
            .assertIsDisplayed()
    }

    fun assertAdditionalInfoTextDisplayed() {
        rule.onNodeWithText(SignInConstants.ADDITIONAL_INFO, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    fun assertSignOutButtonDisplayed() {
        rule.onNodeWithText(SignInConstants.SIGN_OUT_TEXT, useUnmergedTree = true)
            .assertIsDisplayed()
    }


    fun clickOnSignOutButton() {
        rule
            .onAllNodesWithText(SignInConstants.SIGN_OUT_TEXT, useUnmergedTree = true)[1]
            .performTouchInput { click() }
    }

    fun setIncorretCreadentialsEmailAddressToForm() {
        // Email
        rule
            .onAllNodes(hasSetTextAction(), useUnmergedTree = true)[0]
            .performTextInput(SignInConstants.INCORRECT_CREDENTIALS_EMAIL)
    }

    fun setIncorrectCredentialsPasswordToTextField() {
        val textFields = rule.onAllNodes(hasSetTextAction(), useUnmergedTree = true)
        textFields[1].performClick()
            .performTextInput(SignInConstants.INCORRECT_CREDENTIALS_PASSWORD)
    }

    fun checkIncorrectUserCredentialsMessage() {
        rule.waitUntil(timeoutMillis = 20000) {
            rule
                .onAllNodesWithContentDescription(
                    SignInConstants.INCORRECT_CREDENTIALS_MESSAGE,
                    useUnmergedTree = true
                )
                .fetchSemanticsNodes()
                .isNotEmpty()
        }
        rule.onNodeWithText(SignInConstants.INCORRECT_CREDENTIALS_MESSAGE, useUnmergedTree = true)
            .assertIsDisplayed()
    }


}