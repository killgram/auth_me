package com.example.authme.network.r3d3

import com.example.authme.data.Domains
import com.example.authme.network.RetrofitHelper

object R3D3Endpoints {
    val getLoginEndpoint: GetLogin =
        RetrofitHelper.getInstance(Domains.getR3D3Domain()).create(GetLogin::class.java)
    val setPasswordEndpoint: SetPassword =
        RetrofitHelper.getInstance(Domains.getR3D3Domain()).create(SetPassword::class.java)
    val signInEndpoint: SignIn =
        RetrofitHelper.getInstance(Domains.getR3D3Domain()).create(SignIn::class.java)
}
