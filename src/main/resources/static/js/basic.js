$(document).ready(function() {
    //게시글 목록을 불러오는 함수
    function loadPosts() {
        $.ajax({
            url: '/post',  // 게시글 목록을 조회하는 API 엔드포인트
            method: 'GET',
            success: function(data) {
                $('#post-list-container').empty();
                data.forEach(post => {
                    const postElement = `
                        <li>
                            <div>
                                <p>${post.id}</p>
                                <h3>${post.title}</h3>
                                <p>${post.content}</p>
                                <span>가격: ${post.price} 원</span>
                                <p>${post.username}</p>
                            </div>
                            <div>
                                <button class="edit-btn" data-id="${post.id}">수정</button>
                                <button class="delete-btn" data-id="${post.id}">삭제</button>
                            </div>
                        </li>
                    `;
                    $('#post-list-container').append(postElement);
                });
            },
            error: function(error) {
                alert('게시글을 불러오는 데 실패했습니다.');
            }
        });
    }

    // 게시글 수정 버튼 클릭
    $(document).on('click', '.edit-btn', function() {
        const postId = $(this).data('id');
        $.ajax({
            url: `/post/${postId}`,
            method: 'GET',
            success: function(post) {
                $('#modal-title').text('게시글 수정');
                $('#title').val(post.title);
                $('#content').val(post.content);
                $('#price').val(post.price);
                $('#username').val(post.username);
                $('#submit-btn').text('수정');
                $('#submit-btn').data('id', post.id);  // 수정할 ID 저장
                $('#post-modal').show();
            },
            error: function() {
                alert('게시글을 불러오는 데 실패했습니다.');
            }
        });
    });

    // 게시글 삭제 버튼 클릭
    $(document).on('click', '.delete-btn', function() {
        const postId = $(this).data('id');
        if (confirm('정말 삭제하시겠습니까?')) {
            $.ajax({
                url: `/post/${postId}`,
                method: 'DELETE',
                success: function() {
                    alert('게시글이 삭제되었습니다.');
                    loadPosts();
                },
                error: function() {
                    alert('게시글 삭제에 실패했습니다.');
                }
            });
        }
    });

    // 게시글 작성/수정 처리
    $('#post-form').submit(function(event) {
        event.preventDefault();

        const postData = {
            title: $('#title').val(),
            content: $('#content').val(),
            price: $('#price').val(),
            username: $('#username').val()  // 사용자 이름 추가
        };

        const postId = $('#submit-btn').data('id');  // 수정인 경우 ID

        if (postId) {
            // 수정 요청
            $.ajax({
                url: `/post/${postId}`,
                method: 'PUT',
                data: JSON.stringify(postData),
                contentType: 'application/json',
                success: function() {
                    alert('게시글이 수정되었습니다.');
                    loadPosts();
                    $('#post-modal').hide();
                },
                error: function(error) {
                    alert('게시글 수정에 실패했습니다.');
                }
            });
        } else {
            // 작성 요청
            $.ajax({
                url: '/post',
                method: 'POST',
                data: JSON.stringify(postData),
                contentType: 'application/json',
                success: function() {
                    alert('게시글이 등록되었습니다.');
                    loadPosts();
                    $('#post-modal').hide();
                },
                error: function(error) {
                    alert('게시글 등록에 실패했습니다.');
                }
            });
        }
    });

    // 초기 게시글 목록 불러오기
    loadPosts();
});