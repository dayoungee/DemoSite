const main = {
    init: function () {
        console.log("실행")
        const _this = this;
        $('#btn-comment-save').on('click', function () {
            _this.commentSave();
        });
        document.querySelectorAll('#btn-comment-update').forEach(function (item) {
            console.log("tpffprxj");
            item.addEventListener('click', function () { // 버튼 클릭 이벤트 발생시
                console.log("실행122");
                const form = this.closest('form'); // btn의 가장 가까운 조상의 Element(form)를 반환 (closest)
                _this.commentUpdate(form); // 해당 폼으로 업데이트 수행
            });
        });
    },
    /** 댓글 저장 */
    commentSave: function () {
        const data = {
            postsId: $('#postsId').val(),
            comment: $('#comment').val()
        }

        // 공백 및 빈 문자열 체크
        if (!data.comment || data.comment.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/posts/' + data.postsId + '/comments',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('댓글이 등록되었습니다.');
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },
    /** 댓글 수정 */
    commentUpdate: function (form) {
        const data = {
            id: form.querySelector('#id').value,
            postsId: form.querySelector('#postsId').value,
            comment: form.querySelector('#comment-content').value,
            writerUserId: form.querySelector('#writerUserId').value,
            sessionUserId: form.querySelector('#sessionUserId').value
        }
        console.log(data.writerUserId);
        console.log(data.sessionUserId);

        if (data.writerUserId !== data.sessionUserId) {
            alert("본인이 작성한 댓글만 수정 가능합니다.");
            return false;
        }

        console.log("commentWriterID : " + data.writerUserId);
        console.log("sessionUserID : " + data.sessionUserId);

        if (!data.comment || data.comment.trim() === "") {
            alert("공백 또는 입력하지 않은 부분이 있습니다.");
            return false;
        }
        const con_check = confirm("수정하시겠습니까?");
        if (con_check === true) {
            $.ajax({
                type: 'PUT',
                url: '/posts/' + data.postsId + '/comments/' + data.id,
                dataType: 'JSON',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(data)
            }).done(function () {
                window.location.reload();
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    },

    /** 댓글 삭제 */
    commentDelete: function (postsId, commentId, commentWriterId, sessionUserId) {

        if (commentWriterId !== sessionUserId) {
            alert("본인이 작성한 댓글만 삭제 가능합니다.");
        } else {
            const con_check = confirm("삭제하시겠습니까?");
            if (con_check === true) {
                $.ajax({
                    type: 'DELETE',
                    url: '/posts/' + postsId + '/comments/' + commentId,
                    dataType: 'JSON',
                }).done(function () {
                    alert('댓글이 삭제되었습니다.');
                    window.location.reload();
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
            }
        }
    },
    createChatRoom: function () {
        let name = $("#roomName").val();

        if (name === "") {
            alert("방 이름은 필수입니다")
            return false;
        }
        if ($("#" + name).length > 0) {
            alert("이미 존재하는 방입니다")
            return false;
        }

        // 일반채팅 : 최소 방 인원 수는 2, 최대 100명
        if ($("#maxUserCnt").val() <= 1) {
            alert("채팅은 최소 2명 이상이어야 합니다.");
            return false;
        } else if ($("#maxUserCnt").val() > 100) {
            alert("100명 이상은 허용되지 않습니다.");
            return false;
        }

        if (!numberChk()) {
            return false;
        }

        return true;
    }
};

function numberChk(){
    let check = /^[0-9]+$/;
    if (!check.test($("#maxUserCnt").val())) {
        alert("채팅 인원에는 숫자만 입력 가능합니다!!")
        return false;
    }
    return true;
}


main.init();