import React from "react";
import Image from 'next/image'
import {ProfilePicture} from "../ProfilePicture";

export type AssetType = 'IMAGE' | 'PDF_FILE' | 'OTHER';

export interface Asset {
  type: AssetType
  url: string
}


export interface Post {
  author: {
    name: string
    image: string
    id: string
  }
  content: string
  assets: Asset[]
}


export interface PostProps {
  post: Post
}

export const Post: React.VFC<PostProps> = ({post}: PostProps) => {
  return (
    <article className={"shadow-lg"}>
      <div className={'pb-4'}>
        <ProfilePicture user={post.author}/>
      </div>

      <div>
        <p className={'whitespace-pre-line'}>
          {post.content}
        </p>

        <div className={"flex flex-wrap basis-1/3 gap-1"}>
          {post.assets.map((asset, index) => <div key={'asset_' + index} className={'aspect-3/1 relative flex-1 hover:scale-110 hover-z-10' }>
            <PostAsset asset={asset} />
          </div>)}
        </div>
      </div>
    </article>
  )
}

export interface PostAsset {
  asset: Asset
}

export const PostAsset = ({asset}: PostAsset) => {
  if (asset.type == 'IMAGE') {
    return <div>
      <Image src={asset.url} objectFit={'cover'}
             alt={'asset'}
             layout={'fill'}/>
    </div>
  }

  return <div/>
}
