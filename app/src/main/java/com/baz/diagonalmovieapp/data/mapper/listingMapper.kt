package com.baz.diagonalmovieapp.data.mapper

import com.baz.diagonalmovieapp.domain.model.Response

fun Response.toResponse():Response{
    return Response(
        page = page
    )
}